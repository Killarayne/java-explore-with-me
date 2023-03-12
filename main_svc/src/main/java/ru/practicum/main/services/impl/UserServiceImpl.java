package ru.practicum.main.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.main.dto.UserDto;
import ru.practicum.main.exceptions.NameAlreadyExistException;
import ru.practicum.main.exceptions.UserNotExistException;
import ru.practicum.main.mappers.UserMapper;
import ru.practicum.main.repositories.UserRepository;
import ru.practicum.main.services.UserService;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepository.existsByName(userDto.getName())) {
            log.warn("Can't create user with name: " + userDto.getName() + ", the name was used by another user");
            throw new NameAlreadyExistException("Can't create user with name: "
                    + userDto.getName() + ", the name was used by another user");
        }
        log.debug("The user with name " + userDto.getName() + "was created");
        return userMapper.toUserDto(userRepository.save(userMapper.toUserModel(userDto)));
    }

    @Override
    public List<UserDto> getUsers(List<Long> ids, Integer from, Integer size) {
        log.debug("Received users");
        return !ids.isEmpty() ? userMapper.toUserDtoList(userRepository.findAllById(ids))
                : userMapper.toUserDtoList(userRepository.findAllUserWithLimitAndOffset(from, size));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            log.warn("Wrong id, user with id: " + id + " doesn't exist!");
            throw new UserNotExistException("Wrong id, user with id: " + id + " doesn't exist!");
        }
        log.debug("User with id: " + id + " was deleted ");
        userRepository.deleteById(id);
    }
}
