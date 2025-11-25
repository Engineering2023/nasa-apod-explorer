const BASE = 'http://localhost:8080/api/apod';
export const fetchToday = () => fetch(`${BASE}/today`).then(r => r.json());
export const fetchByDate = (date) => fetch(`${BASE}/date/${date}`).then(r => r.json());
export const fetchRecent = (limit = 12) => fetch(`${BASE}/recent?limit=${limit}`).then(r => r.json());
