import { useEffect, useState } from 'react';
import { fetchRecent } from '../api';
import ImageCard from './ImageCard';


export default function Gallery() {
const [images, setImages] = useState([]);


useEffect(() => { fetchRecent().then(setImages); }, []);


return (
<div className="card">
<h2>Recent APOD Gallery</h2>
<div className="gallery-grid">
{images.map(img => <ImageCard key={img.date} data={img} />)}
</div>
</div>
);
}
