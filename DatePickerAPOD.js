import { useState } from 'react';
import { fetchByDate } from '../api';


export default function DatePickerAPOD() {
const [date, setDate] = useState('');
const [data, setData] = useState(null);


const load = () => {
if (date) fetchByDate(date).then(setData);
};


return (
<div className="card">
<h2>Pick a Date</h2>
<input type="date" onChange={e => setDate(e.target.value)} />
<button onClick={load}>Load</button>


{data && (
<div>
<h3>{data.title}</h3>
{data.mediaType === 'video' ? (
<iframe title={data.title} src={data.url} frameBorder="0" width="100%" height="360" />
) : (
<img src={data.url} alt={data.title} className="apod-img" />
)}
<p>{data.explanation}</p>
</div>
)}
</div>
);
}
