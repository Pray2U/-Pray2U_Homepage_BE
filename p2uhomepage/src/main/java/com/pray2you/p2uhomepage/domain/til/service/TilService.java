package com.pray2you.p2uhomepage.domain.til.service;

import com.pray2you.p2uhomepage.domain.til.dto.request.CreateTilRequestDTO;
import com.pray2you.p2uhomepage.domain.til.dto.request.UpdateTilRequestDTO;
import com.pray2you.p2uhomepage.domain.til.dto.response.CreateTilResponseDTO;
import com.pray2you.p2uhomepage.domain.til.dto.response.DeleteTilResponseDTO;
import com.pray2you.p2uhomepage.domain.til.dto.response.ReadTilResponseDTO;
import com.pray2you.p2uhomepage.domain.til.dto.response.UpdateTilResponseDTO;
import com.pray2you.p2uhomepage.domain.til.entity.Til;
import com.pray2you.p2uhomepage.domain.til.repository.TilRepository;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.domain.user.repository.UserRepository;
import com.pray2you.p2uhomepage.global.exception.RestApiException;
import com.pray2you.p2uhomepage.global.exception.errorcode.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TilService {

    private final UserRepository userRepository;
    private final TilRepository tilRepository;

    public CreateTilResponseDTO createTil(long userId, CreateTilRequestDTO requestDTO) {

        User user = userRepository.findByIdAndDeleted(userId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));

        Til til = requestDTO.toEntity(user);

        Til savedTil = tilRepository.save(til);

        return CreateTilResponseDTO.toDTO(savedTil);
    }

    public UpdateTilResponseDTO updateTil(long userId, long tilId, UpdateTilRequestDTO requestDTO) {
        User user = userRepository.findByIdAndDeleted(userId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));

        Til til = tilRepository.findByIdAndDeleted(tilId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_TIL_EXCEPTION));

        if (!user.equals(til.getUser())) {
            throw new RestApiException(UserErrorCode.ACCESS_DENIED);
        }

        Til updateTil = requestDTO.toEntity(til);
        Til updatedTil = tilRepository.save(updateTil);

        return UpdateTilResponseDTO.toDTO(updatedTil);
    }

    public DeleteTilResponseDTO deleteTil(long userId, long tilId) {
        User user = userRepository.findByIdAndDeleted(userId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));

        Til til = tilRepository.findByIdAndDeleted(tilId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_TIL_EXCEPTION));

        if (!user.equals(til.getUser())) {
            throw new RestApiException(UserErrorCode.ACCESS_DENIED);
        }

        Til deletedTil = tilRepository.save(til.delete());

        return DeleteTilResponseDTO.toDTO(deletedTil);
    }

    public Page<ReadTilResponseDTO> readAllTil(Pageable pageable) {
        Page<Til> allTilPage = tilRepository.findAllByDeleted(pageable, false);

        return allTilPage.map(ReadTilResponseDTO::toDTO);
    }

    public ReadTilResponseDTO readTil(long tilId) {
        Til til = tilRepository.findByIdAndDeleted(tilId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_TIL_EXCEPTION));

        return ReadTilResponseDTO.toDTO(til);
    }

    public Page<ReadTilResponseDTO> readUserTil(Pageable pageable, long userId) {
        User user = userRepository.findByIdAndDeleted(userId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));

        Page<Til> tilPage = tilRepository.findAllByUserAndDeleted(pageable, user, false);

        return tilPage.map(ReadTilResponseDTO::toDTO);
    }
}
