package main.java.com.mkaloshyn.myPostsApp.repository.csv;

import main.java.com.mkaloshyn.myPostsApp.model.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class CsvUtil {

    private static final String BUFFER = "src/main/resources/buf.csv";
    private static CsvUtil csvUtil;

    private CsvUtil() {
    }

    public static synchronized CsvUtil getCsvUtil() {
        if (csvUtil == null) {
            csvUtil = new CsvUtil();
        }
        return csvUtil;
    }

    public long defineNextId(List<? extends BasicEntry> listOfEntries) {
        return listOfEntries.stream()
                .map(BasicEntry::getId)
                .max(Comparator.naturalOrder()).orElse(0L) + 1;
    }

    public String defineMatchingString(Long id, String Db) {
        try (BufferedReader reader = new BufferedReader(new FileReader(Db))) {
            String str;
            str = reader.readLine();
            while ((str = reader.readLine()) != null) {
                String[] parts = str.split(";");
                if (!parts[0].equals("id")) {
                    long idFromParts = Long.valueOf(parts[0]);
                    if (id == idFromParts) {
                        return str;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteEntryById(Long id, String Db) {

        File sourceFile = new File(Db);
        File outputFile = new File(BUFFER);

        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String str = reader.readLine();
            writer.write(str);
            writer.newLine();
            while ((str = reader.readLine()) != null) {
                if (!str.isEmpty()) {
                    String[] parts = str.split(";");
                    Long idFromParts = Long.parseLong(parts[0]);
                    if (!idFromParts.equals(id)) {
                        writer.write(str);
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        sourceFile.delete();
        outputFile.renameTo(sourceFile);
    }

    public <T extends BasicEntry> void updateEntry(T entry, String Db) {

        File sourceFile = new File(Db);
        File outputFile = new File(BUFFER);

        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String str;
            str = reader.readLine();
            writer.write(str);
            writer.newLine();
            while ((str = reader.readLine()) != null) {
                if (!str.isEmpty()) {
                    String[] parts = str.split(";");
                    Long idFromParts = Long.parseLong(parts[0]);
                    if (!idFromParts.equals(entry.getId())) {
                        writer.write(str);
                        writer.newLine();
                    } else {
                        if (entry instanceof Post) {
                            writer.write(getStringFromPost((Post) entry));
                            writer.newLine();
                        }
                        if (entry instanceof User) {
                            writer.write(getStringFromUser((User) entry));
                            writer.newLine();
                        }
                        if (entry instanceof Region) {
                            writer.write(getStringFromRegion((Region) entry));
                            writer.newLine();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        sourceFile.delete();
        outputFile.renameTo(sourceFile);
    }

    public String getStringFromUser(User user) {
        StringBuilder sb = new StringBuilder();
        Long id = user.getId();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        Role role = user.getRole();
        sb.append(id)
                .append(";")
                .append(firstName)
                .append(";")
                .append(lastName)
                .append(";")
                .append(role.name());
        return sb.toString();
    }

    public String getStringFromPost(Post post) {
        Long id = post.getId();
        String content = post.getContent();
        Long regionId = post.getRegion().getId();
        LocalDateTime created = post.getCreated();
        LocalDateTime updated = post.getUpdated();
        return updated == null ? (id + ";" + content + ";" + regionId + ";" + created.toString() + ";") :
                (id + ";" + content + ";" + regionId + ";" + created.toString() + ";" + updated.toString());
    }

    public String getStringFromRegion(Region region) {
        Long id = region.getId();
        String regionName = region.getName();
        return id + ";" + regionName;
    }
}
