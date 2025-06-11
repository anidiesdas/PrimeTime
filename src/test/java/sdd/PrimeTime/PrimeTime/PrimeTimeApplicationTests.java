package sdd.PrimeTime.PrimeTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import sdd.PrimeTime.dto.MemberDto;
import sdd.PrimeTime.dto.MonthlyRecapDto;
import sdd.PrimeTime.model.Member;
import sdd.PrimeTime.model.Movie;
import sdd.PrimeTime.model.WatchlistStatus;
import sdd.PrimeTime.repository.MemberRepository;
import sdd.PrimeTime.repository.MovieRepository;
import sdd.PrimeTime.repository.RatingRepository;
import sdd.PrimeTime.service.MemberService;
import sdd.PrimeTime.service.MovieService;
import sdd.PrimeTime.service.PasswordService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PrimeTimeApplicationTests {

	@Mock
	private MemberRepository memberRepository;

	@Mock
	private MovieRepository movieRepository;

	@Mock
	private RatingRepository ratingRepository;

	@Mock
	private PasswordService passwordService;

	@InjectMocks
	private MemberService memberService;

	@InjectMocks
	private MovieService movieService;

	@Test
	@DisplayName("should return list of member dto")
	void getAllMembers() {
		Member member1 = new Member();
		member1.setId(1L);
		member1.setName("giraffe");

		Member member2 = new Member();
		member2.setId(2L);
		member2.setName("tomate");

		List<Member> mockMembers = Arrays.asList(member1, member2);
		when(memberRepository.findAll()).thenReturn(mockMembers);

		List<MemberDto> result = memberService.getAllMembers();

		assertEquals(2, result.size());
		assertEquals("giraffe", result.get(0).getName());
		assertEquals("tomate", result.get(1).getName());
		assertEquals(1L, result.get(0).getId());
		assertEquals(2L, result.get(1).getId());
	}

	@Test
	@DisplayName("should return empty list when no member exists")
	void getEmptyMembers() {
		when(memberRepository.findAll()).thenReturn(Arrays.asList());

		List<MemberDto> result = memberService.getAllMembers();

		assertTrue(result.isEmpty());
	}

	@Test
	@DisplayName("should throw ResponseStatusException with FORBIDDEN when wrong password was entered")
	void deleteMovieFailed() {
		Long movieId = 1L;
		String wrongPassword = "wrongPassword";

		when(passwordService.validatePassword(wrongPassword)).thenReturn(false);

		ResponseStatusException exception = assertThrows(ResponseStatusException.class,
				() -> movieService.deleteMovie(movieId, wrongPassword));

		assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
		assertEquals("INVALID_PASSWORD", exception.getReason());
	}

	@Test
	@DisplayName("should delete movie after correct password was entered")
	void deleteMovieSuccessful() {
		Long movieId = 1L;
		String validPassword = "validPassword";
		Movie mockMovie = new Movie();
		mockMovie.setId(movieId);

		when(passwordService.validatePassword(validPassword)).thenReturn(true);
		when(movieRepository.findById(movieId)).thenReturn(Optional.of(mockMovie));

		assertDoesNotThrow(() -> movieService.deleteMovie(movieId, validPassword));

		verify(movieRepository).delete(mockMovie);
	}

	@Test
	@DisplayName("should return top 5 genres from COMPLETED and DROPPED movies")
	void getTopGenres() {
		Movie movie1 = new Movie();
		movie1.setStatus(WatchlistStatus.COMPLETED);
		movie1.setGenres(List.of("Action", "Drama"));

		Movie movie2 = new Movie();
		movie2.setStatus(WatchlistStatus.COMPLETED);
		movie2.setGenres(List.of("Action", "Comedy"));

		Movie movie3 = new Movie();
		movie3.setStatus(WatchlistStatus.DROPPED);
		movie3.setGenres(List.of("Drama", "Horror"));

		Movie movie4 = new Movie();
		movie4.setStatus(WatchlistStatus.PLAN_TO_WATCH); // soll ignoriert werden
		movie4.setGenres(List.of("Action"));

		List<Movie> allMovies = List.of(movie1, movie2, movie3, movie4);
		when(movieRepository.findAll()).thenReturn(allMovies);

		List<String> result = movieService.getTopGenres();

		assertEquals(4, result.size());
		assertEquals("Action", result.get(0)); // 2x
		assertEquals("Drama", result.get(1));  // 2x
		assertTrue(result.contains("Comedy"));
		assertTrue(result.contains("Horror"));
	}

	@Test
	@DisplayName("should sort genres by count in descending order")
	void getTopGenresSortedByCount() {
		Movie movie1 = new Movie();
		movie1.setStatus(WatchlistStatus.COMPLETED);
		movie1.setGenres(List.of("Action")); // action: 3x

		Movie movie2 = new Movie();
		movie2.setStatus(WatchlistStatus.COMPLETED);
		movie2.setGenres(List.of("Action"));

		Movie movie3 = new Movie();
		movie3.setStatus(WatchlistStatus.DROPPED);
		movie3.setGenres(List.of("Action", "Drama")); // drama: 2x

		Movie movie4 = new Movie();
		movie4.setStatus(WatchlistStatus.COMPLETED);
		movie4.setGenres(List.of("Drama", "Comedy")); // comedy: 1x

		when(movieRepository.findAll()).thenReturn(List.of(movie1, movie2, movie3, movie4));

		List<String> result = movieService.getTopGenres();

		assertEquals(3, result.size());
		assertEquals("Action", result.get(0)); // 3x
		assertEquals("Drama", result.get(1));  // 2x
		assertEquals("Comedy", result.get(2)); // 1x
	}
}

