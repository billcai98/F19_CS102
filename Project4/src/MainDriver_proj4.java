public class MainDriver_proj4 {

    public static void main(String[] args) {

    LewisStructure<String> myChart1 = new LewisStructure<>();

    myChart1.Root("N");
    myChart1.Add("C", "East");
    myChart1.Add("A", "South");
    myChart1.Move("North");
    myChart1.Move("West");
    myChart1.Add("K", "South");
    myChart1.Add("M", "West");
    myChart1.Print();


    LewisStructure<String> myChart2 = new LewisStructure<>();
    myChart2.Root("C");
    myChart2.Add("H", "East");
    myChart2.Move("west");
    myChart2.Add("H", "north");
    myChart2.Move("south");
    myChart2.Add("H", "south");
    myChart2.Move("north");
    myChart2.Add("H", "west");
    myChart2.Move("east");
    myChart2.Print();

    LewisStructure<String> myChart3 = new LewisStructure<>();
    myChart3.Root("S");
    myChart3.Add("H", "north");
    myChart3.Move("south");
    myChart3.Add("C", "east");
    myChart3.Add("H", "north");
    myChart3.Move("south");
    myChart3.Add("H", "south");
    myChart3.Move("north");
    myChart3.Add("C", "east");
    myChart3.Add("H", "north");
    myChart3.Move("south");
    myChart3.Add("H", "south");
    myChart3.Move("north");
    myChart3.Add("I", "east");
    myChart3.Print();

    }

}
