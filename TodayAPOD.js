import { useEffect, useState } from 'react';
import { fetchToday } from '../api';


export default function TodayAPOD() {
const [data, setData] = useState(null);
useEffect(() => { fetchToday().then(setData); }, []);


if (!data)
return <div className="card">Loading...</div>;


return (
<div className="card">
<h2>{data.title}</h2>
{data.mediaType === 'video' ? (
<iframe title={data.title} src={data.url} frameBorder="0" width="100%" height="480" />
) : (
<img src={data.url} alt={data.title} className="apod-img" />
)}
<p>{data.explanation}</p>
<small>{data.date} {data.copyright ? ` - © ${data.copyright}` : ''}</small>
</div>
);
}
