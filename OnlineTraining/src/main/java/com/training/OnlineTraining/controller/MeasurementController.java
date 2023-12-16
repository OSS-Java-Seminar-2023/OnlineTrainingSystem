package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.dto.MeasurementDTO;
import com.training.OnlineTraining.model.Measurement;
import com.training.OnlineTraining.service.MeasurementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
	public String saveMeasurement(@PathVariable UUID contractId, @ModelAttribute MeasurementDTO measurementDto) {
		measurementService.createMeasurement(measurementDto);
		return "index";
	}



//	@GetMapping("/add")
//	public String showAddMeasurementForm(Model model) {
//		model.addAttribute("measurementDTO", new MeasurementDTO());
//		return "addMeasurementForm";
//	}

	//	@GetMapping("/all")
//	public String getAllMeasurements(Model model) {
//		List<Measurement> measurements = measurementService.getAllMeasurements();
//		model.addAttribute("measurements", measurements);
//		return "allMeasurements";
//	}
// 	@PutMapping("/{id}")
//	public String updateMeasurement(@PathVariable UUID id, @ModelAttribute MeasurementDTO measurementDTO) {
//		measurementService.updateMeasurement(id, measurementDTO);
//		return "redirect:/measurements/" + id;
//	}

	//	@DeleteMapping("/{id}")
//	public String deleteMeasurement(@PathVariable UUID id) {
//		measurementService.deleteMeasurement(id);
//		return "redirect:/measurements/all";
//	}

	//	@DeleteMapping
//	public String deleteAllMeasurements() {
//		measurementService.deleteAll();
//		return "redirect:/measurements/all";
//	}
}
