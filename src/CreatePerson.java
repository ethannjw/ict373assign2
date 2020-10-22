import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class CreatePerson {


    public CreatePerson() {

    }

    public static Person createKoisumiSan() {
        try {
            Address addr = new Address(123, "Honkai Impact 1", "Tokyo", 123453);
            Person koisumiSan = new Person("Koisumi 小泉さん", "Unknown", "", "Female", addr, "\"As a recent transfer student, Koizumi-san has always been shrouded in mystery among her classmates. Nothing much is known about Koizumi-san, and even her first name is never mentioned. Nevertheless, her beauty has made her very popular among boys. \\n\\nIt was her classmate Yuu who discovered Koizumi-san's fascination with ramen. Koizumi-san would go to any length to discover new ramen dishes around Japan, even if it means climbing a mountain or failing a school test. What is perhaps most unusual about Koizumi-san is that she has no fear in visiting ramen shops by herself, which are always populated by male customers. The fear and embarrassment that is usually felt by girls who visit Japanese ramen shops alone simply does not register with Koizumi-san in the slightest - eating ramen is the ultimate source of happiness for her.\"");
            Person child = new Person("Ramen", "Unknown", "", "Female", addr, "Child of Koisumi-San is Ramen");
            Person spouse = new Person("Osawa", "Yuu", "Unknown", "Female", addr, "Yuu is a 16 year-old, first-year high school student. She is in the same class as Koizumi-san, and is best friends with Misa and Jun, who are both first-years as well. Yuu lives in an apartment with her parents and older brother, Shuu. She also has an older cousin, Ayane, who very much resembles Yuu in appearance and personality.\n For as long as her friends can remember, Yuu has always had a penchant for girls who looked cute. When she met Koizumi-san, Yuu was fully determined to try to become friends with her. This would involve wanting to tag along with Koizumi-san on her ramen trips, even when uninvited. Her persistence rubs off on Koizumi-san as being obsessive, if not stalker-like.\nShe has a knack for making homemade Japanese meals for her friends and family.");
            koisumiSan.setSpouse(spouse);
            koisumiSan.setChildren(child);
            return koisumiSan;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public static Person createKirito() {
        try {
            Address addr = new Address(123, "Sword Art Online", "Tokyo", 123453);
            Address addr2 = new Address(123, "Sword Art Online 2", "Tokyo", 123453);
            Person kirito = new Person("Kazuto 和人", "Kirigaya 桐ヶ谷", "Kirigaya 桐ヶ谷", "Male", addr, "Kirigaya Kazuto (桐ヶ谷 和人, Kirigaya Kazuto?), born as Narusaka Kazuto (鳴坂 和人なるさか かずと, Narusaka Kazuto?)[1], and known as Kirito (キリト, Kirito?) in «Sword Art Online» (SAO), «ALfheim Online» (ALO), «Gun Gale Online» (GGO), and «Project Alicization», is the protagonist of the main Sword Art Online series.");
            Person midori = new Person("Midori 翠", "Aoi", "Kirigaya 桐ヶ谷", "female", addr, "Kirigaya Midori (桐ヶ谷翠, Kirigaya Midori?) is the mother of Suguha, as well as the aunt and adoptive mother of Kazuto. She is an extremely busy woman and leaves the two kids to take care of themselves, only coming back home late in the night and leaving early in the morning, though she still shows love and care for them.");
            Person minetaka = new Person("Minetaka", "Kirigaya 桐ヶ谷", "Kirigaya 桐ヶ谷", "male", addr, "Unknown");
            Person asuna = new Person("Asuna 明日奈", "Yuuki 結城", "Kirigaya 桐ヶ谷", "female", addr2, "Yuuki Asuna (結城 明日奈, Yūki Asuna?), known as Asuna (アスナ, Asuna?) in «Sword Art Online» (SAO), «ALfheim Online» (ALO), as well as «Project Alicization», where she had temporarily used the Stacia (ステイシア, Suteishia?) account, is the main heroine of the Sword Art Online series[7], the deuteragonist of the Aincrad Arc, as well as the protagonist of the Mother's Rosario side story. She is the daughter of the former CEO of RECT Inc.. Asuna was one of the 10,000 players trapped in «Sword Art Online», where she was the sub-leader of the «Knights of the Blood» (KoB) guild.[3] Her skills with the rapier had her earned her the nickname «The Flash» (閃光, Senkō?).");
            Person yui = new Person("Yui ユイ", "", "", "female", addr, "Yui (ユイ, Yui?) is an Artificial Intelligence (AI), found by Kirito and Asuna around the forests of the 22nd Floor of the floating castle Aincrad. Her official name is «Yui-MHCP001» (Mental Health Counseling Program 001).");
            Person yui2 = new Person("Yui2 ユイ", "", "", "female", addr, "Yui2 (ユイ, Yui?) is an Artificial Intelligence (AI), found by Kirito and Asuna around the forests of the 22nd Floor of the floating castle Aincrad. Her official name is «Yui-MHCP001» (Mental Health Counseling Program 001).");
            Person kuroHime = new Person("Kurohime", "Princess", "", "female", addr2, "Black Princess");
            kirito.setChildren(yui);
            kirito.setChildren(kuroHime);
            asuna.setChildren(yui);
            kuroHime.setChildren(yui2);
            try {
                kirito.setParents(midori);
            } catch (Exception e) {
                System.out.println(e);
            }
            try {
                kirito.setParents(minetaka);
            } catch (Exception e) {
                System.out.println(e);
            }
            try {
                kirito.setSpouse(asuna);
            } catch (Exception e) {
                System.out.println(e);
            }
            try {
                yui.setChildren(kuroHime);
            } catch (Exception e) {
                System.out.println(e);
            }
            return kirito;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
