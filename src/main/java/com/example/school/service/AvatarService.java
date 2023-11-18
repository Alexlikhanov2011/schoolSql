package com.example.school.service;

import com.example.school.exception.StudentNotFoundException;
import com.example.school.model.Avatar;
import com.example.school.model.Student;
import com.example.school.repository.AvatarRepository;
import com.example.school.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

@Service
public class AvatarService {
    private final static Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;
    private final String avatarsDir;

    public AvatarService(StudentRepository studentRepository,
                         AvatarRepository avatarRepository,
                         @Value("${path.to.avatars.folder}") String avatarsDir) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
        this.avatarsDir = avatarsDir;
    }

    @Transactional
    public void upload(long studentId, MultipartFile file) throws IOException {
        logger.info("studentCount was invoked with argument {}:{}",studentId,file);
        var student = studentRepository.findById(studentId)
                .orElseThrow(StudentNotFoundException::new);

        var dir = Path.of(avatarsDir);
        if (!dir.toFile().exists()) {
            Files.createDirectories(dir);
        }
        var path = saveFile(file, student);

        Avatar avatar = avatarRepository.findByStudentId(studentId).orElse(new Avatar());
        avatar.setFilePath(path);
        avatar.setData(file.getBytes());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setStudent(student);
        avatarRepository.save(avatar);
    }

    private String saveFile(MultipartFile file, Student student) {
        logger.info("studentCount was invoked with argument {}",file);
        var dotIndex = file.getOriginalFilename().lastIndexOf('.');
        var ext = file.getOriginalFilename().substring(dotIndex + 1);
        var path = avatarsDir + "/" + student.getId() + "_" + student.getName() + "." + ext;
        try (var in = file.getInputStream();
             var out = new FileOutputStream(path)) {
            in.transferTo(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }

    @Transactional
    public Avatar find(long studentId) {
        logger.info("studentCount was invoked with argument {}",studentId);
        return avatarRepository.findByStudentId(studentId).orElse(null);
    }
public Collection <Avatar> find(int page, int pageSize){
    logger.info("studentCount was invoked with argument {}:{}",page, pageSize);
        return avatarRepository.findAll(PageRequest.of(page, pageSize)).getContent();
}
}
