import java.io.*;
import java.util.*;

public class Starting_5 {
    private static final String USER_FILE = "users.txt";
    private static final String STATS_FILE = "player_stats.csv";
    private static final String TERMS_FILE = "stat_terms.txt";

    private static Scanner sc = new Scanner(System.in);
    private static String loggedInUser = null;

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("\n--- 메인 메뉴 ---");
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("3. 농구 소개");
            System.out.println("4. 검색");
            if (loggedInUser != null) {
                System.out.println("5. 로그아웃");
                System.out.println("6. 게시판");
            }
            System.out.println("0. 종료");
            System.out.print("선택: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1": signUp(); break;
                case "2": login(); break;
                case "3": showBasketballIntro(); break;
                case "4": search(); break;
                case "5":
                    if (loggedInUser != null) logout();
                    break;
                case "6":
                    if (loggedInUser != null) bbsMenu();
                    break;
                case "0": System.exit(0);
                default: System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private static void signUp() throws IOException {
        System.out.println("[회원가입]");
        System.out.print("새 아이디 입력: ");
        String id = sc.nextLine();

        BufferedReader reader = new BufferedReader(new FileReader(USER_FILE));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.split(" ")[0].equals(id)) {
                System.out.println("이미 존재하는 아이디입니다.");
                reader.close();
                return;
            }
        }
        reader.close();

        System.out.print("비밀번호 입력: ");
        String pw = sc.nextLine();

        BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true));
        writer.write(id + " " + pw);
        writer.newLine();
        writer.close();

        System.out.println("회원가입이 완료되었습니다.");
    }

    private static void login() throws IOException {
        System.out.println("[로그인]");
        System.out.print("아이디: ");
        String id = sc.nextLine();
        System.out.print("비밀번호: ");
        String pw = sc.nextLine();

        BufferedReader reader = new BufferedReader(new FileReader(USER_FILE));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length == 2 && parts[0].equals(id) && parts[1].equals(pw)) {
                loggedInUser = id;
                System.out.println("환영합니다, " + id + "님!");
                reader.close();
                return;
            }
        }
        reader.close();
        System.out.println("아이디 또는 비밀번호가 틀렸습니다.");
    }

    private static void logout() {
        System.out.println("로그아웃 되었습니다: " + loggedInUser);
        loggedInUser = null;
    }

    private static void showBasketballIntro() {
        System.out.println("[농구 소개]");
        System.out.println("- 5명이 한 팀이 되어 상대 팀의 골대에 공을 넣는 스포츠입니다.");
        System.out.println("- NBA는 세계에서 가장 유명한 프로 농구 리그입니다.");
    }

    private static void search() throws IOException {
        System.out.println("[검색]");
        System.out.print("선수 이름 또는 스탯 용어 입력: ");
        String input = sc.nextLine().trim().toLowerCase();

        // 1. 선수 검색
        BufferedReader statReader = new BufferedReader(new FileReader(STATS_FILE));
        String headerLine = statReader.readLine();
        String[] headers = headerLine.split(",");

        boolean playerFound = false;
        String line;
        while ((line = statReader.readLine()) != null) {
            if (line.toLowerCase().contains(input)) {
                String[] values = line.split(",");
                System.out.println("\n[선수 스탯]");
                for (String header : headers) {
                    System.out.printf("%-15s", header);
                }
                System.out.println();
                for (String value : values) {
                    System.out.printf("%-15s", value);
                }
                System.out.println();
                playerFound = true;
            }
        }
        statReader.close();

        if (playerFound) return;  // 찾았으면 스탯만 출력하고 끝

        // 2. 스탯 용어 설명 검색
        BufferedReader termReader = new BufferedReader(new FileReader(TERMS_FILE));
        boolean termFound = false;

        while ((line = termReader.readLine()) != null) {
            if (line.toLowerCase().startsWith(input + ":")) {
                System.out.println("\n[스탯 용어 설명]");
                System.out.println(line);
                termFound = true;
                break;
            }
        }
        termReader.close();

        if (!termFound) {
            System.out.println("선수 또는 스탯 용어를 찾을 수 없습니다.");
        }
    }

    private static void bbsMenu() throws IOException {
        System.out.println("[게시판 메뉴]");
        System.out.println("1. 게시물 작성");
        System.out.println("2. 댓글 작성");
        System.out.print("선택: ");
        String choice = sc.nextLine();

        switch (choice) {
            case "1": createPost(); break;
            case "2": writeComment(); break;
            default: System.out.println("잘못된 선택입니다.");
        }
    }

    private static void createPost() throws IOException {
        System.out.println("[게시물 작성]");
        System.out.print("제목: ");
        String title = sc.nextLine();
        System.out.print("내용: ");
        String content = sc.nextLine();

        BufferedWriter writer = new BufferedWriter(new FileWriter("posts.txt", true));
        writer.write("작성자: " + loggedInUser);
        writer.newLine();
        writer.write("제목: " + title);
        writer.newLine();
        writer.write("내용: " + content);
        writer.newLine();
        writer.write("--------");
        writer.newLine();
        writer.close();
        System.out.println("게시물이 작성되었습니다.");
    }

    private static void writeComment() throws IOException {
        System.out.println("[댓글 작성]");
        System.out.print("댓글 내용: ");
        String comment = sc.nextLine();

        BufferedWriter writer = new BufferedWriter(new FileWriter("comments.txt", true));
        writer.write("작성자: " + loggedInUser + " / " + comment);
        writer.newLine();
        writer.close();
        System.out.println("댓글이 작성되었습니다.");
    }
}
