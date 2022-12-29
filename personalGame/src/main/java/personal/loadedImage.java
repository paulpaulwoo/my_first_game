package personal;

public class loadedImage {
    public String prefix;
    public String suffix;
    public int animationLength;
    public int identifier;

    public loadedImage (String prefix, String suffix, int animationLength, int identifier) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.animationLength = animationLength;
        this.identifier = identifier;
    }
}
