package sdd.PrimeTime.PrimeTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import sdd.PrimeTime.dto.MemberDto;
import sdd.PrimeTime.model.Member;
import sdd.PrimeTime.model.Movie;
import sdd.PrimeTime.repository.MemberRepository;
import sdd.PrimeTime.repository.MovieRepository;
import sdd.PrimeTime.service.MemberService;
import sdd.PrimeTime.service.MovieService;
import sdd.PrimeTime.service.PasswordService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
		verify(memberRepository, times(1)).findAll();
	}

	@Test
	@DisplayName("should return INVALID_PASSWORD when wrong password was entered")
	void deleteMovieFailed() {
		Long movieId = 1L;
		String wrongPassword = "wrongPassword";

		when(passwordService.validatePassword(wrongPassword)).thenReturn(false);

		String result = movieService.deleteMovie(movieId, wrongPassword);

		assertEquals("INVALID_PASSWORD", result);
	}

	@Test
	@DisplayName("should delete movie after correct password was entered")
	void deleteMovieSuccessful() {
		Long movieId = 1L;
		String validPassword = "validPassword";
		Movie mockMovie = new Movie();
		mockMovie.setId(movieId);
		mockMovie.setTitle("Power Rangers");

		when(passwordService.validatePassword(validPassword)).thenReturn(true);
		when(movieRepository.findById(movieId)).thenReturn(Optional.of(mockMovie));

		String result = movieService.deleteMovie(movieId, validPassword);

		assertEquals("Movie deleted successfully.", result);
		verify(passwordService, times(1)).validatePassword(validPassword);
		verify(movieRepository, times(1)).findById(movieId);
		verify(movieRepository, times(1)).delete(mockMovie);
	}
}