package hei.school.act_agricole.service;

import hei.school.act_agricole.dto.request.CreateActivityRequest;
import hei.school.act_agricole.dto.request.CreateAttendanceRequest;
import hei.school.act_agricole.dto.response.ActivityResponse;
import hei.school.act_agricole.dto.response.AttendanceResponse;
import hei.school.act_agricole.entity.ActivityAttendance;
import hei.school.act_agricole.entity.CollectivityActivity;
import hei.school.act_agricole.exception.BadRequestException;
import hei.school.act_agricole.exception.NotFoundException;
import hei.school.act_agricole.repository.ActivityAttendanceRepository;
import hei.school.act_agricole.repository.CollectivityActivityRepository;
import hei.school.act_agricole.repository.CollectivityRepository;
import hei.school.act_agricole.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    private final CollectivityActivityRepository activityRepo = new CollectivityActivityRepository();
    private final ActivityAttendanceRepository attendanceRepo = new ActivityAttendanceRepository();
    private final MemberRepository memberRepo = new MemberRepository();
    private final CollectivityRepository collectivityRepo = new CollectivityRepository();

    // POST /collectivities/{id}/activities
    public List<ActivityResponse> createActivities(String collectivityId, List<CreateActivityRequest> requests) {
        try {
            if (collectivityRepo.findById(collectivityId).isEmpty())
                throw new NotFoundException("Collectivity not found");
            List<ActivityResponse> responses = new ArrayList<>();
            for (CreateActivityRequest req : requests) {
                CollectivityActivity activity = new CollectivityActivity();
                activity.setId(UUID.randomUUID().toString());
                activity.setCollectivityId(collectivityId);
                activity.setTitle(req.getTitle());
                activity.setDescription(req.getDescription());
                activity.setActivityDate(req.getActivityDate());
                activity.setMandatory(req.isMandatory());
                activityRepo.save(activity);
                responses.add(toActivityResponse(activity));
            }
            return responses;
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }

    // GET /collectivities/{id}/activities
    public List<ActivityResponse> getActivities(String collectivityId) {
        try {
            if (collectivityRepo.findById(collectivityId).isEmpty())
                throw new NotFoundException("Collectivity not found");
            List<CollectivityActivity> activities = activityRepo.findByCollectivityId(collectivityId);
            return activities.stream().map(this::toActivityResponse).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }

    // POST /collectivities/{id}/activities/{activityId}/attendance
    public List<AttendanceResponse> recordAttendance(String collectivityId, String activityId,
                                                     List<CreateAttendanceRequest> requests) {
        try {
            if (collectivityRepo.findById(collectivityId).isEmpty())
                throw new NotFoundException("Collectivity not found");
            CollectivityActivity activity = activityRepo.findById(activityId)
                    .orElseThrow(() -> new NotFoundException("Activity not found"));
            if (!activity.getCollectivityId().equals(collectivityId))
                throw new BadRequestException("Activity does not belong to this collectivity");

            List<AttendanceResponse> responses = new ArrayList<>();
            for (CreateAttendanceRequest req : requests) {
                if (memberRepo.findById(req.getMemberId()).isEmpty())
                    throw new NotFoundException("Member not found: " + req.getMemberId());
                if (attendanceRepo.findByActivityAndMember(activityId, req.getMemberId()).isPresent())
                    throw new BadRequestException("Attendance already recorded for member " + req.getMemberId());

                if (req.isExcused() && (req.getReason() == null || req.getReason().trim().isEmpty()))
                    throw new BadRequestException("Reason required when excused");

                ActivityAttendance attendance = new ActivityAttendance();
                attendance.setId(UUID.randomUUID().toString());
                attendance.setActivityId(activityId);
                attendance.setMemberId(req.getMemberId());
                attendance.setPresent(req.isPresent());
                attendance.setExcused(req.isExcused());
                attendance.setReason(req.getReason());
                attendanceRepo.save(attendance);
                responses.add(toAttendanceResponse(attendance));
            }
            return responses;
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }

    // GET /collectivities/{id}/activities/{activityId}/attendance
    public List<AttendanceResponse> getAttendance(String collectivityId, String activityId) {
        try {
            if (collectivityRepo.findById(collectivityId).isEmpty())
                throw new NotFoundException("Collectivity not found");
            CollectivityActivity activity = activityRepo.findById(activityId)
                    .orElseThrow(() -> new NotFoundException("Activity not found"));
            if (!activity.getCollectivityId().equals(collectivityId))
                throw new BadRequestException("Activity does not belong to this collectivity");

            List<ActivityAttendance> attendances = attendanceRepo.findByActivityId(activityId);
            List<AttendanceResponse> responses = new ArrayList<>();
            for (ActivityAttendance att : attendances) {
                var memberOpt = memberRepo.findById(att.getMemberId());
                if (memberOpt.isPresent()) {
                    AttendanceResponse resp = toAttendanceResponse(att);
                    resp.setFirstName(memberOpt.get().getFirstName());
                    resp.setLastName(memberOpt.get().getLastName());
                    responses.add(resp);
                }
            }
            return responses;
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }

    private ActivityResponse toActivityResponse(CollectivityActivity a) {
        ActivityResponse resp = new ActivityResponse();
        resp.setId(a.getId());
        resp.setTitle(a.getTitle());
        resp.setDescription(a.getDescription());
        resp.setActivityDate(a.getActivityDate());
        resp.setMandatory(a.isMandatory());
        return resp;
    }

    private AttendanceResponse toAttendanceResponse(ActivityAttendance a) {
        AttendanceResponse resp = new AttendanceResponse();
        resp.setMemberId(a.getMemberId());
        resp.setPresent(a.isPresent());
        resp.setExcused(a.isExcused());
        resp.setReason(a.getReason());
        return resp;
    }
}
