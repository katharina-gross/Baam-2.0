package com.example.baam2.service;

import com.example.baam2.dto.request.SessionCreateDTO;
import com.example.baam2.dto.request.SessionUpdateDTO;
import com.example.baam2.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.example.baam2.dto.response.SessionResponseDTO;
import com.example.baam2.exception.CustomException;
import com.example.baam2.model.SessionModel;
import com.example.baam2.repository.SessionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    public SessionService(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    public SessionResponseDTO createSession(SessionCreateDTO request){
        if (request.getTitle() == null || request.getTitle().isEmpty())
            throw new CustomException("TITLE_IS_NULL","Session title cannot be null or empty");
        if (request.getOwnerId() == null)
            throw new CustomException("OWNER_ID_IS_NULL","Session owner id cannot be null or empty");
        if (!(userRepository.existsById(request.getOwnerId())))
            throw new CustomException("OWNER_ID_NOT_EXIST","Session owner id does not exist");

        SessionModel sessionModel = new SessionModel();

        sessionModel.setTitle(request.getTitle());
        sessionModel.setOwner(userRepository.findById(request.getOwnerId()).orElseThrow());

        String qrToken = "QR link";

        sessionModel.setQrToken(qrToken);
        sessionModel.setActive(true);
        sessionModel.setCreateAt(LocalDateTime.now());

        sessionModel = sessionRepository.save(sessionModel);

        SessionResponseDTO response = new SessionResponseDTO();

        response.setId(sessionModel.getId());
        response.setTitle(sessionModel.getTitle());
        response.setQrToken(sessionModel.getQrToken());
        response.setCreatedAt(sessionModel.getCreateAt());
        return response;
    }

    public void deleteSession(Long id){
        if (!(sessionRepository.existsById(id)))
            throw new CustomException("ID_NOT_EXIST","Session id does not exist");
        sessionRepository.deleteById(id);
    }

    @Transactional
    public void updateSessionName(Long id, SessionUpdateDTO request){
        if (!(sessionRepository.existsById(id)))
            throw new CustomException("ID_NOT_EXIST","Session id does not exist");
        SessionModel sessionModel = sessionRepository.findById(id).orElseThrow();
        sessionModel.setTitle(request.getTitle());
    }

    public List<SessionResponseDTO> getAllSessions(){
        return sessionRepository.findAll().stream().map(sessionModel -> new SessionResponseDTO(sessionModel.getId(),sessionModel.getTitle(), sessionModel.getQrToken(), sessionModel.getCreateAt())).collect(Collectors.toList());
    }
}
