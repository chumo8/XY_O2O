package com.xyo2o.service.user;


import com.xyo2o.domain.user.User;
import com.xyo2o.dto.user.UserQueryPageDTO;

import java.util.List;

public interface UserService {

	List<User> findAll(UserQueryPageDTO dto);

	User testNickName(String nickname);

	User testUserName(String username);

	int insertUser(User user);

	int updateUser(User user, User user_update);

	int delUserById(List<Integer> ids);

	User findOneById(Integer id);
}
