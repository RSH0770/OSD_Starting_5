import java.io.*;
import java.util.*;

public class Login {
    private static final String USER_FILE = "users.txt";
    private static final String POST_FILE = "posts.txt";
    private static final String COMMENT_FILE = "comments.txt";

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String loggedInUser = null;

        while (true) {
            System.out.println("\n--- 메인 메뉴 ---");
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("3. 농구 소개 보기");
            System.out.println("0. 종료");
            System.out.print("선택: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    signUp(sc);
                    break;
                case "2":
                    loggedInUser = login(sc);
                    if (loggedInUser != null) {
                        userMenu(sc, loggedInUser);
                    }
                    break;
                case "3":
                    showBasketballIntro();
                    break;
                case "0":
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                default:
                    System.out.println("잘못된 메뉴 선택입니다.");
            }
        }
    }

    private static void signUp(Scanner sc) throws IOException {
        System.out.println("[회원가입]");
        System.out.print("새 아이디 입력: ");
        String id = sc.nextLine();
        System.out.print("새 비밀번호 입력: ");
        String pw = sc.nextLine();

        BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true));
        writer.write(id + " " + pw);
        writer.newLine();
        writer.close();

        System.out.println("회원가입이 완료되었습니다.");
    }

    private static String login(Scanner sc) throws IOException {
        System.out.println("[로그인]");
        while (true) {
            System.out.print("아이디: ");
            String id = sc.nextLine();
            System.out.print("비밀번호: ");
            String pw = sc.nextLine();

            BufferedReader reader = new BufferedReader(new FileReader(USER_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2 && parts[0].equals(id) && parts[1].equals(pw)) {
                    reader.close();
                    System.out.println("로그인 성공. 환영합니다, " + id + "님!");
                    return id;
                }
            }
            reader.close();
            System.out.println("아이디 또는 비밀번호가 올바르지 않습니다. 다시 시도해주세요.");
        }
    }

    private static void userMenu(Scanner sc, String user) throws IOException {
        while (true) {
            System.out.println("\n--- 사용자 메뉴 ---");
            System.out.println("1. 게시글 작성");
            System.out.println("2. 게시글 보기 및 댓글 작성");
            System.out.println("3. 로그아웃");
            System.out.print("선택: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    createPost(sc, user);
                    break;
                case "2":
                    viewPostsAndComment(sc, user);
                    break;
                case "3":
                    System.out.println(user + "님, 로그아웃 되었습니다.");
                    return;
                default:
                    System.out.println("올바르지 않은 선택입니다.");
            }
        }
    }

    private static void showBasketballIntro() {
        System.out.println("\n--- 농구 소개 ---");
        System.out.println("농구는 두 팀이 각각 다섯 명의 선수가 코트에서 경기를 펼치며, 공을 상대팀의 링(골대)에 던져 득점을 얻는 팀 스포츠입니다.");
        System.out.println("빠른 템포와 전략적인 움직임이 특징이며, 전 세계적으로 매우 인기 있는 스포츠입니다.");
    }

    private static void createPost(Scanner sc, String user) throws IOException {
        System.out.println("[게시글 작성]");
        System.out.print("제목: ");
        String title = sc.nextLine();
        System.out.print("내용: ");
        String content = sc.nextLine();

        BufferedWriter writer = new BufferedWriter(new FileWriter(POST_FILE, true));
        writer.write(user + "|" + title + "|" + content);
        writer.newLine();
        writer.close();

        System.out.println("게시글이 성공적으로 등록되었습니다.");
    }

    private static void viewPostsAndComment(Scanner sc, String user) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(POST_FILE));
        List<String> posts = new ArrayList<>();
        String line;
        int index = 1;
        System.out.println("\n[게시글 목록]");
        while ((line = reader.readLine()) != null) {
            posts.add(line);
            String[] parts = line.split("\\|", 3); // 사용자|제목|내용
            System.out.println(index++ + ". [" + parts[0] + "] " + parts[1]);
        }
        reader.close();

        if (posts.isEmpty()) {
            System.out.println("등록된 게시글이 없습니다.");
            return;
        }

        System.out.print("조회할 게시글 번호 선택 (0: 뒤로가기): ");
        int choice = Integer.parseInt(sc.nextLine());
        if (choice == 0 || choice > posts.size()) return;

        String[] selected = posts.get(choice - 1).split("\\|", 3);
        System.out.println("\n제목: " + selected[1]);
        System.out.println("작성자: " + selected[0]);
        System.out.println("내용: " + selected[2]);

        System.out.println("\n--- 댓글 ---");
        showComments(choice);  // choice는 게시글 인덱스

        System.out.print("댓글을 작성하시겠습니까? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("댓글 내용: ");
            String comment = sc.nextLine();
            BufferedWriter commentWriter = new BufferedWriter(new FileWriter(COMMENT_FILE, true));
            commentWriter.write(choice + "|" + user + "|" + comment);
            commentWriter.newLine();
            commentWriter.close();
            System.out.println("댓글이 등록되었습니다.");
        }
    }

    private static void showComments(int postIndex) throws IOException {
        File file = new File(COMMENT_FILE);
        if (!file.exists()) {
            System.out.println("등록된 댓글이 없습니다.");
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        boolean hasComment = false;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|", 3);
            int commentPostIndex;
            try {
                commentPostIndex = Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                continue; // 잘못된 포맷 무시
            }

            if (commentPostIndex == postIndex) {
                System.out.println("- " + parts[1] + ": " + parts[2]);
                hasComment = true;
            }
        }
        reader.close();

        if (!hasComment) {
            System.out.println("등록된 댓글이 없습니다.");
        }
    }
}
