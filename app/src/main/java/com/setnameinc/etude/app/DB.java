package com.setnameinc.etude.app;

import java.util.List;

public interface DB {

    List<String> getUser();
    List<String> getLessons();
    List<String> getDays();

    void setUser(int id);




}
