import TodayAPOD from './components/TodayAPOD';
import DatePickerAPOD from './components/DatePickerAPOD';
import Gallery from './components/Gallery';


export default function App() {
return (
<div className="container">
<h1>NASA APOD Explorer</h1>
<TodayAPOD />
<DatePickerAPOD />
<Gallery />
</div>
);
}
