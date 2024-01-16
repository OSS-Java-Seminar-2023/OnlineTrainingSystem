package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.dto.MeasurementDTO;
import com.training.OnlineTraining.model.Measurement;
import com.training.OnlineTraining.service.MeasurementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/measurements")
public class MeasurementController {

	private final MeasurementService measurementService;

	@GetMapping("/{contractId}")
	public String showMeasurementForm(@PathVariable UUID contractId, Model model) {
		model.addAttribute("contractId", contractId);
		model.addAttribute("measurementDto", new MeasurementDTO());
		return "client/measurement_form";
	}

	@PostMapping("/{contractId}")
	public String createMeasurement(@PathVariable UUID contractId,
								   @Valid MeasurementDTO measurementDto,
								   BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "client/measurement_form";
		}

		measurementService.createMeasurement(measurementDto);

		return "index";
	}

	@GetMapping("/personal/{contractId}")
	public String getPersonalMeasurements(@PathVariable UUID contractId, Model model) {
		List<Measurement> measurements = measurementService.getMeasurementsByContractIdSortedByDate(contractId);

		model.addAttribute("measurements", measurements);

		return "client/personal_measurements";
	}


	@GetMapping("/personal/{contractId}/update/{measurementId}")
	public String showUpdateForm(@PathVariable UUID contractId, @PathVariable UUID measurementId, Model model) {
		MeasurementDTO measurementDto = measurementService.getMeasurementById(measurementId);
		model.addAttribute("contractId", contractId);
		model.addAttribute("measurementId", measurementId);
		model.addAttribute("measurementDto", measurementDto);
		return "client/update_measurement_form";
	}

	@PutMapping("/personal/{contractId}/update/{measurementId}")
	public String updateMeasurement(@PathVariable UUID contractId,
									@PathVariable UUID measurementId,
								    @Valid MeasurementDTO measurementDto,
									BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "client/update_measurement_form";
		}

		measurementService.updateMeasurement(measurementId, measurementDto);

		return "redirect:/measurements/personal/{contractId}";
	}

	@DeleteMapping("/personal/{contractId}/delete/{measurementId}")
	public String deleteMeasurement(@PathVariable UUID contractId,
									@PathVariable UUID measurementId) {
		measurementService.deleteMeasurement(measurementId);
		return "redirect:/measurements/personal/{contractId}";
	}





}
