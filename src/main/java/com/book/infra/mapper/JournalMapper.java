package com.book.infra.mapper;

import com.book.application.journal.JournalDto;
import com.book.domain.Journal;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author jinx
 */
@Mapper(componentModel = "spring")
public interface JournalMapper {

    JournalDto convert(Journal journal);

    List<JournalDto> convert(List<Journal> journal);
}
