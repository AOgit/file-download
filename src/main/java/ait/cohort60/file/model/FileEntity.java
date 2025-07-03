package ait.cohort60.file.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String contentType;

    @Lob
    @Column(nullable = false)
    private byte[] data;
}