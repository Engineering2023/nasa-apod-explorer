package com.example.apod.service;
import com.example.apod.cache.SimpleCache;
import com.example.apod.model.ApodResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class NasaService {

@Value("${nasa.apod.base-url}")
private String baseUrl;

@Value("${nasa.apod.key:}")
private String apiKey;

@Value("${apod.cache.ttl-ms}")
private long cacheTtl;

@Value("${apod.cache.max-size}")
private int cacheMaxSize;

private WebClient webClient;
private SimpleCache<String, ApodResponse> cache;

@PostConstruct
public void init() {
this.webClient = WebClient.builder().baseUrl(baseUrl).build();
this.cache = new SimpleCache<>(cacheTtl, cacheMaxSize);
}

public ApodResponse fetchApod(String date) {
final String key = (date == null || date.isBlank()) ? "today" : date;
ApodResponse cached = cache.get(key);
if (cached != null) return cached;

Mono<ApodResponse> mono = webClient.get()
.uri(uriBuilder -> uriBuilder
.queryParam("api_key", apiKey)
.queryParamIfPresent("date", date == null || date.isBlank() ? java.util.Optional.empty() : java.util.Optional.of(date))
.build())
.accept(MediaType.APPLICATION_JSON)
.retrieve()
.bodyToMono(ApodResponse.class);

ApodResponse resp = mono.block();
if (resp != null) cache.put(key, resp);
return resp;
}

public List<ApodResponse> fetchRecent(int limit) {
List<ApodResponse> out = new ArrayList<>();
for (int i = 0; i < limit; i++) {
LocalDate d = LocalDate.now().minusDays(i);
String dateStr = d.toString();
ApodResponse r = fetchApod(dateStr);
if (r != null) out.add(r);
}
return out;
}
}
