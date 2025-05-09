import java.io.*;
import java.util.*;

public class Starting_5 {
    private static final String USER_FILE = "users.txt";
    private static final String STATS_FILE = "player_stats.csv";
    private static final String TERMS_FILE = "stat_terms.txt";

    private static String loggedInUser = null;
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("\n--- 메인 메뉴 ---");
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("3. 농구 소개");
            System.out.println("4. 검색 (선수/스탯 용어)");
            if (loggedInUser != null) {
                System.out.println("5. 로그아웃");
                System.out.println("6. 게시판");
            }
            System.out.println("0. 종료");
            System.out.print("선택: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    signUp();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    showBasketballIntro();
                    break;
                case "4":
                    search();
                    break;
                case "5":
                    if (loggedInUser != null) logout();
                    break;
                case "6":
                    if (loggedInUser != null) bbsMenu();
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private static void signUp() throws IOException {
        System.out.println("[회원가입]");

        String id;
        while (true) {
            System.out.print("아이디 입력: ");
            id = sc.nextLine().trim();
            if (isIdDuplicate(id)) {
                System.out.println("이미 존재하는 아이디입니다. 다른 아이디를 입력하세요.");
            } else {
                break;
            }
        }

        System.out.print("비밀번호 입력: ");
        String pw = sc.nextLine();

        String email;
        while (true) {
            System.out.print("이메일 입력: ");
            email = sc.nextLine().trim();
            if (!email.contains("@")) {
                System.out.println("유효하지 않은 이메일입니다. '@'가 포함되어야 합니다.");
            } else {
                break;
            }
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true));
        writer.write(id + " " + pw + " " + email);
        writer.newLine();
        writer.close();

        System.out.println("회원가입이 완료되었습니다.");
    }

    private static boolean isIdDuplicate(String id) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(USER_FILE));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length >= 1 && parts[0].equals(id)) {
                reader.close();
                return true;
            }
        }
        reader.close();
        return false;
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
            if (parts.length >= 2 && parts[0].equals(id) && parts[1].equals(pw)) {
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

        if (playerFound) return;

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
        while (true) {
            System.out.println("\n[게시판 메뉴]");
            System.out.println("1. 게시물 목록 보기");
            System.out.println("2. 게시물 작성");
            System.out.println("3. 댓글 작성");
            System.out.println("4. 뒤로 가기");
            System.out.print("선택: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1": viewPosts(); break;
                case "2": createPost(); break;
                case "3": writeComment(); break;
                case "4": return;
                default: System.out.println("잘못된 선택입니다.");
            }
        }
    }

    // 게시물 목록 보기 + 선택해서 본문 + 댓글 보기
    private static void viewPosts() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("posts.txt"));
        String line;
        List<String[]> posts = new ArrayList<>();
        String author = "", title = "", content = "";
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("작성자:")) {
                author = line.substring(5).trim();
            } else if (line.startsWith("제목:")) {
                title = line.substring(3).trim();
            } else if (line.startsWith("내용:")) {
                content = line.substring(3).trim();
            } else if (line.equals("--------")) {
                posts.add(new String[]{author, title, content});
            }
        }
        reader.close();

        if (posts.isEmpty()) {
            System.out.println("게시물이 없습니다.");
            return;
        }

        for (int i = 0; i < posts.size(); i++) {
            System.out.printf("%d. [%s] %s\n", i + 1, posts.get(i)[0], posts.get(i)[1]);
        }

        System.out.print("조회할 게시글 번호 입력 (0은 취소): ");
        int sel = Integer.parseInt(sc.nextLine());
        if (sel < 1 || sel > posts.size()) return;

        String[] post = posts.get(sel - 1);
        System.out.println("\n[게시글 내용]");
        System.out.println("작성자: " + post[0]);
        System.out.println("제목: " + post[1]);
        System.out.println("내용: " + post[2]);

        // 댓글 출력
        BufferedReader cr = new BufferedReader(new FileReader("comments.txt"));
        System.out.println("\n[댓글]");
        boolean found = false;
        while ((line = cr.readLine()) != null) {
            if (line.startsWith("#COMMENT")) continue;
            if (line.startsWith("게시물번호:")) {
                int postNum = Integer.parseInt(line.substring("게시물번호:".length()).trim());
                if (postNum == sel) {
                    String authorLine = cr.readLine();
                    String contentLine = cr.readLine();
                    System.out.println(authorLine + " - " + contentLine);
                    found = true;
                }
            }
        }
        cr.close();
        if (!found) System.out.println("댓글이 없습니다.");
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
        System.out.print("댓글을 달 게시물 번호 입력: ");
        int postNumber = Integer.parseInt(sc.nextLine());

        System.out.print("댓글 내용: ");
        String comment = sc.nextLine();

        BufferedWriter writer = new BufferedWriter(new FileWriter("comments.txt", true));
        writer.write("#COMMENT");
        writer.newLine();
        writer.write("게시물번호: " + postNumber);
        writer.newLine();
        writer.write("작성자: " + loggedInUser);
        writer.newLine();
        writer.write("내용: " + comment);
        writer.newLine();
        writer.close();

        System.out.println("댓글이 작성되었습니다.");
    }

}