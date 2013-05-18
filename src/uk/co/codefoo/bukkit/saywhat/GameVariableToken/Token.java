package uk.co.codefoo.bukkit.saywhat.GameVariableToken;

public class Token {
	public static final String TokenPrefix = "%%";
	private String name;
	private String description;
	
	public Token(String token, String description)
	{
		this.name = String.format("%s%s", TokenPrefix, token);
		this.description = description;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDescription()
	{
		return description;
	}
}
