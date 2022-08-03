package prgrms.project.stuti.domain.studygroup.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import prgrms.project.stuti.domain.studygroup.controller.dto.StudyGroupCreateRequest;
import prgrms.project.stuti.domain.studygroup.controller.dto.StudyGroupUpdateRequest;
import prgrms.project.stuti.domain.studygroup.repository.service.dto.StudyGroupApplyDto;
import prgrms.project.stuti.domain.studygroup.repository.service.dto.StudyGroupCreateDto;
import prgrms.project.stuti.domain.studygroup.repository.service.dto.StudyGroupDeleteDto;
import prgrms.project.stuti.domain.studygroup.repository.service.dto.StudyGroupIdResponse;
import prgrms.project.stuti.domain.studygroup.repository.service.dto.StudyGroupUpdateDto;
import prgrms.project.stuti.domain.studygroup.repository.service.studygroup.StudyGroupService;

@RestController
@RequestMapping("/api/v1/study-groups")
@RequiredArgsConstructor
public class StudyGroupRestController {

	private final StudyGroupService studyGroupService;

	@PostMapping
	public ResponseEntity<StudyGroupIdResponse> createStudyGroup(@AuthenticationPrincipal Long memberId,
		@Valid @ModelAttribute StudyGroupCreateRequest createRequest) {
		StudyGroupCreateDto createDto = StudyGroupMapper.toStudyGroupCreateDto(memberId, createRequest);
		StudyGroupIdResponse idResponse = studyGroupService.createStudyGroup(createDto);
		URI uri = URI.create("/api/v1/study-groups/" + idResponse.studyGroupId());

		return ResponseEntity.created(uri).body(idResponse);
	}

	@PostMapping("/{studyGroupId}")
	public ResponseEntity<StudyGroupIdResponse> applyStudyGroup(@AuthenticationPrincipal Long memberId,
		@PathVariable Long studyGroupId) {
		StudyGroupApplyDto applyDto = StudyGroupMapper.toStudyGroupApplyDto(memberId, studyGroupId);
		StudyGroupIdResponse idResponse = studyGroupService.applyStudyGroup(applyDto);

		return ResponseEntity.ok(idResponse);
	}

	@PatchMapping("/{studyGroupId}")
	public ResponseEntity<StudyGroupIdResponse> updateStudyGroup(@AuthenticationPrincipal Long memberId,
		@PathVariable Long studyGroupId, @Valid @ModelAttribute StudyGroupUpdateRequest updateRequest) {
		StudyGroupUpdateDto updateDto = StudyGroupMapper.toStudyGroupUpdateDto(memberId, studyGroupId, updateRequest);
		StudyGroupIdResponse idResponse = studyGroupService.updateStudyGroup(updateDto);

		return ResponseEntity.ok(idResponse);
	}

	@DeleteMapping("/{studyGroupId}")
	public ResponseEntity<StudyGroupIdResponse> deleteStudyGroup(@AuthenticationPrincipal Long memberId,
		@PathVariable Long studyGroupId) {
		StudyGroupDeleteDto deleteDto = StudyGroupMapper.toStudyGroupDeleteDto(memberId, studyGroupId);
		StudyGroupIdResponse idResponse = studyGroupService.deleteStudyGroup(deleteDto);

		return ResponseEntity.ok(idResponse);
	}
}
