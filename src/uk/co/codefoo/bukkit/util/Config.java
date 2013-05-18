package uk.co.codefoo.bukkit.util;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;

public class Config {

	public static ConcurrentSkipListMap<String, String> loadMapFromConfig(Configuration config ,String sectionKeyName)
	{
		if (config == null)
		{
			return null;
		}
		if (sectionKeyName == null)
		{
			return null;
		}

		ConfigurationSection configSection = 
				config.getConfigurationSection(sectionKeyName);
		if (configSection == null)
		{
			return null;
		}
		
		ConcurrentSkipListMap<String, String> resultMap =new ConcurrentSkipListMap<String, String>();
		Map<String, Object> loadMap = configSection.getValues(false);
		for (Map.Entry<String, Object> loadMapEntry : loadMap.entrySet())
		{
			resultMap.put(loadMapEntry.getKey(), (String)loadMapEntry.getValue());
		}
		return resultMap;
	}

}
