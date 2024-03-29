package ru.practicum.main.services;

import ru.practicum.main.dto.CompilationDto;
import ru.practicum.main.dto.NewCompilationDto;
import ru.practicum.main.dto.UpdateCompilationRequest;

import java.util.List;

public interface CompilationService {
    CompilationDto createCompilation(NewCompilationDto newCompilationDto);

    CompilationDto getCompilation(Long compId);

    List<CompilationDto> getCompilations(Boolean pinned, Integer from, Integer size);

    void deleteCompilation(Long compId);

    CompilationDto updateCompilation(Long compId, UpdateCompilationRequest updateCompilationRequest);
}
