export default function ImageCard({ data }) {
return (
<div className="image-card">
{data.mediaType === 'video' ? (
<a href={data.url} target="_blank" rel="noreferrer" className="video-link">Open Video</a>
) : (
<img src={data.url} alt={data.title} />
)}
<p>{data.title}</p>
<small>{data.date}</small>
</div>
);
}
