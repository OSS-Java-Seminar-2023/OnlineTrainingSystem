package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.dto.MeasurementDTO;
import com.training.OnlineTraining.model.Measurement;
import com.training.OnlineTraining.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/measurements")
public class MeasurementController {

	private final MeasurementService measurementService;

	@Autowired
	public MeasurementController(MeasurementService measurementService) {
		this.measurementService = measurementService;
	}

	@GetMapping("/add")
	public String showAddMeasurementForm(Model model) {
		model.addAttribute("measurementDTO", new MeasurementDTO());
		return "addMeasurementForm";
	}

	@PostMapping("/create")
	public String createMeasurement(@ModelAttribute MeasurementDTO measurementDTO) {
		measurementService.createMeasurement(measurementDTO);
		return "";
	}

	@GetMapping("/{id}")
	public String getMeasurementById(@PathVariable UUID id, Model model) {
		Measurement measurement = measurementService.getMeasurementById(id).orElse(null);
		model.addAttribute("measurement", measurement);
		return "measurementDetails";
	}

	@GetMapping("/all")
	public String getAllMeasurements(Model model) {
		List<Measurement> measurements = measurementService.getAllMeasurements();
		model.addAttribute("measurements", measurements);
		return "allMeasurements";
	}

	@PutMapping("/{id}")
	public String updateMeasurement(@PathVariable UUID id, @ModelAttribute MeasurementDTO measurementDTO) {
		measurementService.updateMeasurement(id, measurementDTO);
		return "redirect:/measurements/" + id;
	}

	@DeleteMapping("/{id}")
	public String deleteMeasurement(@PathVariable UUID id) {
		measurementService.deleteMeasurement(id);
		return "redirect:/measurements/all";
	}

	@DeleteMapping
	public String deleteAllMeasurements() {
		measurementService.deleteAll();
		return "redirect:/measurements/all";
	}
}
