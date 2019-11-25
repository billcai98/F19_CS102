public class MainDriver_proj4 {

    public static void main(String[] args) {

    LewisStructure<String> myChart = new LewisStructure<>();

    myChart.Root("N");
    myChart.Add("C", "East");
    myChart.Add("A", "South");
    myChart.Move("North");
    myChart.Move("West");
    myChart.Add("K", "South");
    myChart.Add("M", "West");

    myChart.Print();

    }

}
