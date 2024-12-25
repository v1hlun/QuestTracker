package com.example.kurs.Service;

import com.example.kurs.Dao.QuestLogRepository;
import com.example.kurs.entyty.QuestLog;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuestLogService {

    private final QuestLogRepository questLogRepository;

    public QuestLogService(QuestLogRepository questLogRepository) {
        this.questLogRepository = questLogRepository;
    }

    public List<QuestLog> getAllQuestLogs() {
        return questLogRepository.findAll();
    }

    public QuestLog getQuestLogById(Long id) {
        return questLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QuestLog not found with id: " + id));
    }

    public QuestLog createQuestLog(QuestLog questLog) {
        return questLogRepository.save(questLog);
    }

    // Удалить запись лога возможно если лог старше 30 дней
    public void deleteQuestLog(Long id) {
        QuestLog questLog = questLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QuestLog not found with id: " + id));

        // Ограничение: удалять только старые записи
        if (questLog.getTimestamp().isBefore(LocalDateTime.now().minusDays(30))) {
            questLogRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cannot delete QuestLog: Record is too recent.");
        }
    }
}
