import java.beans.Transient;
import org.junit.*;

public class add1test{
    @Test
    public void test1foradd1(){
        add1 add1_var = new add1();
        Assert.assertEquals(7, add1_var.addnumbers(4, 3));
        Assert.assertEquals(-7, add1_var.addnumbers(-4, -3));
        Assert.assertEquals(201, add1_var.addnumbers(201, 0));
    }
}

