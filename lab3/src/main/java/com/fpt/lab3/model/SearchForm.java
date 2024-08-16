package com.fpt.lab3.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchForm {
    private String searchText;
    private String searchType;
}
