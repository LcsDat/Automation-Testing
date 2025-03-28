public class Demo {
    public static void main(String[] args) {
        String str = "sdf4578sadf".replaceAll("[^0-9]","");

        System.out.println(str);
        demo.MONDAY.setLocator("hello world");
        System.out.println(demo.MONDAY.getLocator());

    }

    enum demo {

        MONDAY();

        public void setLocator(String locator){
            this.locator = locator;
        }
        public String getLocator(){
            return locator;
        }

        private String locator;

    }
}
