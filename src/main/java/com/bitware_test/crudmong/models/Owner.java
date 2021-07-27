package com.bitware_test.crudmong.models;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@ToString
@Document( collection = "owner")
public class Owner {

    @Id
    private String client_id;

    @Indexed(unique = true)
    private String user_name = "";

    private String password = "";

    private String first_name = "";

    private String last_name = "";

    @Indexed(unique = true)
    private String mail = "";

    private int age = -1;

    private float heigth = -1;

    private float weigth = -1;

    private float imc = -1;

    private float geb = -1;

    private float eta = -1;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date created_date = new Date();

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date update_date = new Date();
}
