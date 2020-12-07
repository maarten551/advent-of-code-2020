package nl.bobbeldijk.day7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputParser {
    private HashMap<String, Bag> result = new HashMap<>();

    public Map<String, Bag> parseInput(List<String> input) {
        Pattern parseLineRegex = Pattern.compile("^([a-z ]+?) bags contain ([a-z0-9 ,]+)\\.$");
        Pattern parseRuleRegex = Pattern.compile("^(\\d+) ([a-z ]+) bags?$");

        for (String inputLine : input) {
            var matcher = parseLineRegex.matcher(inputLine);

            if (!matcher.matches()) {
                throw new Error(String.format("Failed to parse rule %s", inputLine));
            }

            var parsingBag = getBagOrCreateByType(matcher.group(1));

            if (matcher.group(2).equals("no other bags")) {
                continue;
            }

            parsingBag.setRules(Arrays.stream(matcher.group(2).split(","))
                    .map(String::trim)
                    .map((String unparsedRule) -> {
                        var ruleMatcher = parseRuleRegex.matcher(unparsedRule);

                        if (ruleMatcher.matches()) {
                            return ruleMatcher;
                        }

                        throw new Error(String.format("Failed to parse rule %s", unparsedRule));
                    })
                    .map((Matcher ruleMatcher) ->
                            new BagRule(Integer.parseInt(ruleMatcher.group(1)), getBagOrCreateByType(ruleMatcher.group(2)))
                    ).collect(Collectors.toList()));
        }

        return result;
    }

    private Bag getBagOrCreateByType(String type) {
        return result.computeIfAbsent(type, Bag::new);
    }
}
