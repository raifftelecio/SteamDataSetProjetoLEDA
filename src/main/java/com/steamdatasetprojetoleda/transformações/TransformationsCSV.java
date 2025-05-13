package com.steamdatasetprojetoleda.transformações;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class TransformationsCSV {
    private static final Logger LOGGER = Logger.getLogger(TransformationsCSV.class.getName());

    private static String formatReleaseDate(String dateString) {
        DateTimeFormatter inputFormat;
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date;
        try {
            inputFormat = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
            date = LocalDate.parse(dateString, inputFormat);
        } catch (DateTimeParseException e) {
            try {
                inputFormat = DateTimeFormatter.ofPattern("MMM yyyy dd", Locale.ENGLISH);
                date = LocalDate.parse(dateString + " 01", inputFormat);
            } catch (Exception ex) {
                LOGGER.log(Level.WARNING, "Erro ao converter data: {0}", ex.getMessage());
                return dateString;
            }
        }
        return date.format(outputFormat);
    }

    public static void transformReleaseDates(Path filePath) {
        File inputFile = filePath.toFile();
        File outputFile = new File(inputFile.getParent(), "games_formated_release_data.csv");

        try (
            Reader reader = new FileReader(inputFile);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
            Writer writer = new FileWriter(outputFile);
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(csvParser.getHeaderMap().keySet().toArray(new String[0])))
        ) {
            for (CSVRecord record : csvParser) {
                String[] row = new String[record.size()];
                for (int i = 0; i < record.size(); i++) {
                    row[i] = record.get(i);
                }
                row[2] = formatReleaseDate(record.get(2));
                csvPrinter.printRecord((Object[]) row);

            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro na transformação de datas: {0}", e.getMessage());
        }
    }

    public static void filterLinuxCompatibleGames(Path filePath) {
        processCSV(filePath, "games_linux.csv", "Metacritic score", "true");
        /*
        usamos a coluna "Metacritic score" pois ao analisar o arquivo games.csv, 
        notamos uma coluna a "mais" que está preenchida de zeros, por conta disso, 
        utilizamos a coluna sequente a Linux para que, assim, o projeto seja executado da forma correta.
        */
    }

    public static void filterPortugueseSupportedGames(Path filePath) {
        processCSV(filePath, "portuguese_supported_games.csv", 10, "Portuguese - Brazil");
    }

    private static void processCSV(Path filePath, String outputFileName, String columnName, String expectedValue) {
        File inputFile = filePath.toFile();
        File outputFile = new File(inputFile.getParent(), outputFileName);

        try (
            Reader reader = new FileReader(inputFile);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
            Writer writer = new FileWriter(outputFile);
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(csvParser.getHeaderMap().keySet().toArray(new String[0])))
        ) {
            for (CSVRecord record : csvParser) {
                if (record.get(columnName).equalsIgnoreCase(expectedValue)) {
                    csvPrinter.printRecord(record);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao processar CSV: {0}", e.getMessage());
        }
    }

    private static void processCSV(Path filePath, String outputFileName, int columnIndex, String expectedValue) {
        File inputFile = filePath.toFile();
        File outputFile = new File(inputFile.getParent(), outputFileName);

        try (
            Reader reader = new FileReader(inputFile);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
            Writer writer = new FileWriter(outputFile);
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(csvParser.getHeaderMap().keySet().toArray(new String[0])))
        ) {
            for (CSVRecord record : csvParser) {
                String[] languages = record.get(columnIndex).replaceAll("[\\[\\]']", "").split(",\\s*");
                for (String lang : languages) {
                    if (expectedValue.equalsIgnoreCase(lang.trim())) {
                        csvPrinter.printRecord(record);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao processar CSV: {0}", e.getMessage());
        }
    }

    public static void executeTransformacoes() {
        Path gamesPath = Paths.get("src", "test", "java", "com", "steamdatasetprojetoleda", "games.csv");
        Path transformedGamesPath = Paths.get("src", "test", "java", "com", "steamdatasetprojetoleda", "games_formated_release_data.csv");
        
        LOGGER.info("Iniciando transformação de datas...");
        transformReleaseDates(gamesPath);
        LOGGER.info("Transformação de datas concluída.");
        System.out.println("");

        LOGGER.info("Filtrando jogos compatíveis com Linux...");
        filterLinuxCompatibleGames(transformedGamesPath);
        LOGGER.info("Filtro de jogos Linux concluído.");
        System.out.println("");
        
        LOGGER.info("Filtrando jogos com suporte ao português...");
        filterPortugueseSupportedGames(transformedGamesPath);
        LOGGER.info("Filtro de jogos em português concluído.");
    }
}
