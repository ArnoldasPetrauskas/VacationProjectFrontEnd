package com.VacationProject.VacationProjectFrontEnd.Organizer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Organizers {

    @NonNull
    private List<Organizer> organizers;
}
