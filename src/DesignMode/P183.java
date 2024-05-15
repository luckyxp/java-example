package DesignMode;

/**
 * @author climb.xu
 * @date 2022/5/9 14:23
 */
public class P183 {
    public static void main(String[] args) {
        HEA television = new Television();
        HEA airConditioner = new AirConditioner();
        HEA refrigeratory = new Refrigeratory();


        Firm haierTelevision = new Haier(television);//海尔电视
        Firm haierAirConditioner = new Haier(airConditioner);//海尔空调
        Firm haierRefrigeratory = new Haier(refrigeratory);//海尔冰箱

        Firm tclTelevision = new TCL(television);//tcl电视
        Firm tclAirConditioner = new TCL(airConditioner);//tcl空调
        Firm tclRefrigeratory = new TCL(refrigeratory);//tcl冰箱

        Firm hisenseTelevision = new Hisense(television);//海信电视
        Firm hisenseAirConditioner = new Hisense(airConditioner);//海信空调
        Firm hisenseRefrigeratory = new Hisense(refrigeratory);//海信冰箱

        haierTelevision.start("客厅");
        haierAirConditioner.start("客厅");
        haierRefrigeratory.start("客厅");

        tclTelevision.start("客厅");
        tclAirConditioner.start("客厅");
        tclRefrigeratory.start("客厅");

        hisenseTelevision.start("客厅");
        hisenseAirConditioner.start("客厅");
        hisenseRefrigeratory.start("客厅");



    }
}


interface HEA {
    void run(String addr,String firm);//启动家电
}
//电视机
class Television implements HEA {
    @Override
    public void run(String addr,String firm) {System.out.println(firm+"电视机开始运行,在"+addr);}
}
//空调
class AirConditioner implements HEA {
    @Override
    public void run(String addr,String firm) {System.out.println(firm+"空调开始运行,在"+addr);}
}
//冰箱
class Refrigeratory implements HEA {
    @Override
    public void run(String addr,String firm) {System.out.println(firm+"冰箱开始运行,在"+addr);}
}

//家电制造厂商抽象类
abstract class Firm {
    protected HEA hea;
    public abstract void start(String addr);
}

//海尔
class Haier extends Firm {
    public Haier(HEA hea) {
        super.hea = hea;
    }
    @Override
    public void start(String addr) {
        super.hea.run(addr,"海尔");
    }
}
//TCL
class TCL extends Firm {
    public TCL(HEA hea) {
        super.hea = hea;
    }
    @Override
    public void start(String addr) {
        super.hea.run(addr,"TCL");
    }
}
//海信
class Hisense extends Firm {
    public Hisense(HEA hea) {
        super.hea = hea;
    }
    @Override
    public void start(String addr) {
        super.hea.run(addr,"海信");
    }
}