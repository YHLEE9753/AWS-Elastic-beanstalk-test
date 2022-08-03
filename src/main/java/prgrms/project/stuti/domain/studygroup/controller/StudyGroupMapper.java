package prgrms.project.stuti.domain.studygroup.controller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import prgrms.project.stuti.domain.studygroup.controller.dto.StudyGroupCreateRequest;
import prgrms.project.stuti.domain.studygroup.controller.dto.StudyGroupUpdateRequest;
import prgrms.project.stuti.domain.studygroup.model.Region;
import prgrms.project.stuti.domain.studygroup.repository.service.dto.StudyGroupApplyDto;
import prgrms.project.stuti.domain.studygroup.repository.service.dto.StudyGroupCreateDto;
import prgrms.project.stuti.domain.studygroup.repository.service.dto.StudyGroupDeleteDto;
import prgrms.project.stuti.domain.studygroup.repository.service.dto.StudyGroupUpdateDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudyGroupMapper {

	public static StudyGroupCreateDto toStudyGroupCreateDto(Long memberId, StudyGroupCreateRequest createRequest) {
		return StudyGroupCreateDto
			.builder()
			.memberId(memberId)
			.imageFile(createRequest.imageFile())
			.title(createRequest.title())
			.topic(createRequest.topic())
			.isOnline(createRequest.isOnline())
			.region(createRequest.isOnline() ? Region.ONLINE : createRequest.region())
			.preferredMBTIs(createRequest.preferredMBTIs())
			.numberOfRecruits(createRequest.numberOfRecruits())
			.startDateTime(createRequest.startDateTime())
			.endDateTime(createRequest.endDateTime())
			.description(createRequest.description())
			.build();
	}

	public static StudyGroupUpdateDto toStudyGroupUpdateDto(Long memberId, Long studyGroupId,
		StudyGroupUpdateRequest updateRequest) {
		return StudyGroupUpdateDto
			.builder()
			.memberId(memberId)
			.studyGroupId(studyGroupId)
			.title(updateRequest.title())
			.imageFile(updateRequest.imageFile())
			.description(updateRequest.description())
			.build();
	}

	public static StudyGroupApplyDto toStudyGroupApplyDto(Long memberId, Long studyGroupId) {
		return new StudyGroupApplyDto(memberId, studyGroupId);
	}

	public static StudyGroupDeleteDto toStudyGroupDeleteDto(Long memberId, Long studyGroupId) {
		return new StudyGroupDeleteDto(memberId, studyGroupId);
	}
}
