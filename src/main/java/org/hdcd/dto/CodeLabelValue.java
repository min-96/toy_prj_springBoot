package org.hdcd.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CodeLabelValue {


    private String value;
    private String label;


    public CodeLabelValue(String value, String label){
        this.value=value;
        this.label=label;
    }



}
