package com.hcl.bank.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.hcl.bank.dto.UserDTO;
import com.hcl.bank.exception.InvalidAccountException;
import com.hcl.bank.exception.InvalidUserException;
import com.hcl.bank.model.Account;
import com.hcl.bank.model.User;
import com.hcl.bank.repository.UserRepository;
import com.hcl.bank.response.UserAccount;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveToDo() throws InvalidAccountException, InvalidUserException {

		UserDTO userDto = new UserDTO();
		userDto.setAddress("sadsdfsdf");
		userDto.setUserName("yaseen hgkds");
		userDto.setEmail("sk@gmail.com");
		userDto.setPassword("237657948315958");
		User user = new User();
		user.setAddress("sadsdfsdf");
		user.setUserName("yaseen hgkds");
		user.setEmail("sk@gmail.com");
		user.setPassword("237657948315958");
		Account account = new Account();
		account.setAccountNumber(764747875l);
		List<Account> accounts = new ArrayList<Account>();

		user.setAccounts(accounts);

		when(userRepository.save(user)).thenReturn(user);
		User result = userService.saveUser(userDto);
		assertEquals("sadsdfsdf", result.getAddress());
	}

	@Test
	public void getUserAccountsTest() {

		List<Account> accounts = new ArrayList<Account>();
		accounts.add(new Account(1l, 231332424233l, "budjehjj", 200000l));
		accounts.add(new Account(2l, 23133242433l, "bujehjj", 2000000l));

		List<UserAccount> userAccounts = accounts.stream().map(accountList -> {
			UserAccount userAccount = new UserAccount();
			BeanUtils.copyProperties(accountList, userAccount);
			return userAccount;
		}).collect(Collectors.toList());

		when(userAccounts).thenReturn(userAccounts);

		assertEquals(2, userAccounts.size());
	}
	
	
	/*
	 * @Test public void deleteUserTest() throws UserNotFoundExcption{ User user =
	 * new User();
	 * 
	 * user.setUserID(2l); user.setAddress("sadsdfsdf");
	 * user.setUserName("yaseen hgkds"); user.setEmail("sk@gmail.com");
	 * user.setPassword("237657948315958"); Account account = new Account();
	 * account.setAccountNumber(764747875l); List<Account> accounts = new
	 * ArrayList<Account>();
	 * 
	 * user.setAccounts(accounts);
	 * 
	 * userService.deleteUser(user.getUserId());
	 * 
	 * User dbUser = userRepository.findById(user.getUserId()).get();
	 * 
	 * verify(userRepository, times(1)).delete(dbUser); }
	 */
	
	

}
