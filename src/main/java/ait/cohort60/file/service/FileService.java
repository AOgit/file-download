package ait.cohort60.file.service;

import ait.cohort60.file.dao.FileRepository;
import ait.cohort60.file.dto.FileResponseDto;
import ait.cohort60.file.model.FileEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileResponseDto storeFile(MultipartFile multipartFile) {
        try {
            FileEntity file = new FileEntity();
            file.setFileName(multipartFile.getOriginalFilename());
            file.setContentType(multipartFile.getContentType());
            file.setData(multipartFile.getBytes());

            FileEntity saved = fileRepository.save(file);

            FileResponseDto dto = new FileResponseDto();
            dto.setFilename(saved.getFileName());
            dto.setDownloadUrl("/files/download/" + saved.getId());

            return dto;
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить файл", e);
        }
    }

    public FileEntity getFile(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Файл не найден"));
    }
}

