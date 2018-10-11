package com.csye6225.fall2018.coursepractise.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.coursepractise.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.coursepractise.datamodel.Lecture;

public class LecturesService {
	static HashMap<Long, Lecture> lect_Map = InMemoryDatabase.getLectureDB();
	
	//Getting a list of all lectures
	//GET "..webapi/lectures"
	public List<Lecture> getAllLectures(){
		//Getting the list
		ArrayList<Lecture> list = new ArrayList<>();
		for(Lecture lect : lect_Map.values()) {
			list.add(lect);
		}
		return list;
	}
	
	//Adding a lecture
	public void addLecture(List<String> notes, List<String> materials) {
		long nextAvailabledId = lect_Map.size() +1;
		Lecture lect = new Lecture(nextAvailabledId, notes, materials);
		lect_Map.put(nextAvailabledId, lect);
	}
	
	public Lecture addLecture(Lecture lect) {
		long nextAvailabledId = lect_Map.size() + 1;
		lect.setLectureId(nextAvailabledId);
		lect_Map.put(nextAvailabledId, lect);
		return lect_Map.get(nextAvailabledId);
	}
	
	//Getting one lecture
	public Lecture getLecture(Long lectId) {
		return lect_Map.get(lectId);
	}
	
	//Deleting a lecture
	public Lecture deleteLecture(Long lectId) {
		Lecture deleteLectDetails = lect_Map.get(lectId);
		lect_Map.remove(lectId);
		return deleteLectDetails;
	}
	
	//Updating a lecture Info
	public Lecture updateLecture(Long lectId, Lecture lect) {
		Lecture oldLectObject = lect_Map.get(lectId);
		lectId = oldLectObject.getLectureId();
		lect.setLectureId(lectId);
		//publishing new values
		lect_Map.put(lectId, lect);
		return lect;
	}
	
	//get lectures of a course ??
}
