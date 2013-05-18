package uk.co.codefoo.bukkit.saywhat.Abbreviation;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import org.bukkit.configuration.Configuration;

import uk.co.codefoo.bukkit.util.Config;

public class Abbreviations
{
    private static final String AbbreviationsConfigKey = "abbreviations";

    private ConcurrentSkipListMap<String, String> abbreviationsMap;
    
    public String get(String abbreviationKey)
    {
        return abbreviationsMap.get(abbreviationKey);
    }
    
    public String getAbbreviationDescription(String abbreviationKey)
    {
        String result;
        if (abbreviationsMap.size() == 0)
        {
            result="No abbreviations defined.";
            return result;
        }

        String abbreviationValue = abbreviationsMap.get(abbreviationKey);
        if (abbreviationValue==null)
        {
            result=String.format("There is no '%s' abbreviation.", abbreviationKey);
            return result;
        }
    
        result = String.format("'%s' sends the message '%s'.", abbreviationKey, abbreviationValue);
        
        return result;
    }

    public String getAllAbbreviationKeys()
    {
        String result;
        if (abbreviationsMap.size() == 0)
        {
            result="No abbreviations defined.";
            return result;
        }

        StringBuilder abbreviationsListBuilder = new StringBuilder();
        abbreviationsListBuilder.append("Abbreviations: ");
        for (Map.Entry<String, String> abbreviationMapEntry : abbreviationsMap.entrySet())
        {
            abbreviationsListBuilder.append(abbreviationMapEntry.getKey());
            abbreviationsListBuilder.append(' ');
        }
        result=abbreviationsListBuilder.toString();
        return result;
    }

    public String getAbbreviationNotFoundText(String abbreviationKey)
    {
        return String.format(
                "Could not find abbreviation '%s'." ,
                abbreviationKey);
    }
    
    public boolean loadFromConfig(Configuration config)
    {
        abbreviationsMap = Config.loadMapFromConfig(config, AbbreviationsConfigKey);

        return abbreviationsMap != null;

    }

    public boolean put(String abbreviationKey, String abbreviationValue)
    {
        String replacedAbbreviationValue = abbreviationsMap.put(abbreviationKey, abbreviationValue);
        return replacedAbbreviationValue != null;
    }
    
    public boolean remove(String abbreviationKey)
    {
        String removedAbbreviationValue = abbreviationsMap.remove(abbreviationKey);
        return removedAbbreviationValue != null;
    }

    public boolean saveToConfig(Configuration config)
    {
        config.createSection(AbbreviationsConfigKey, abbreviationsMap);
        return true;
    }
 }
