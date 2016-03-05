package org.koushik.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.koushik.javabrains.messenger.database.DatabaseClass;
import org.koushik.javabrains.messenger.model.Profile;

public class ProfileService {
	Map<String,Profile> profiles = DatabaseClass.getProfiles();
	/*
	public ProfileService(){
		profiles.put("emad", new Profile(1l,"emad"));
		
	}
	public List<Profile> getAllprofiles(){
		
		return new ArrayList<Profile>(profiles.values());
	}
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}
	public Profile addProfile(Profile Profile){
		Profile.setId(profiles.size()+1);
		profiles.put(Profile.getProfileName(), Profile);
		return Profile;
	}
	public Profile updateProfile(Profile Profile){
		if(Profile.getProfileName().isEmpty()){
			return null;
		}
		profiles.put(Profile.getProfileName(), Profile);
		return Profile;
	}
	public void  removeProfile(String profileName){
		 profiles.remove(profileName);
	}
	*/

	
}


