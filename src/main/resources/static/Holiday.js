const holidays = {
  '2025-01-26': 'Republic Day',
  '2025-03-29': 'Holi',
  '2025-04-10': 'Ram Navami',
  '2025-04-14': 'Ambedkar Jayanti',
  '2025-04-18': 'Good Friday',
  '2025-05-23': 'Buddha Purnima',
  '2025-08-15': 'Independence Day',
  '2025-08-26': 'Janmashtami',
  '2025-10-02': 'Gandhi Jayanti',
  '2025-10-11': 'Dussehra',
  '2025-10-31': 'Diwali',
  '2025-11-01': 'Govardhan Puja',
  '2025-11-03': 'Bhai Dooj',
  '2025-11-07': 'Chhath Puja',
  '2025-12-25': 'Christmas Day'
};

const calendarContainer = document.getElementById('calendarContainer');
const months = [
  'January', 'February', 'March', 'April', 'May', 'June',
  'July', 'August', 'September', 'October', 'November', 'December'
];

const weekdays = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'];

for (let month = 0; month < 12; month++) {
  const section = document.createElement('div');
  section.className = 'month-section animate__animated animate__fadeInUp';

  const title = document.createElement('div');
  title.className = 'month-title';
  title.textContent = months[month];
  section.appendChild(title);

  const header = document.createElement('div');
  header.className = 'calendar';
  weekdays.forEach(day => {
    const div = document.createElement('div');
    div.className = 'weekday-header';
    div.textContent = day;
    header.appendChild(div);
  });
  section.appendChild(header);

  const grid = document.createElement('div');
  grid.className = 'calendar';

  const firstDay = new Date(2025, month, 1);
  const lastDay = new Date(2025, month + 1, 0);

  let offset = (firstDay.getDay() + 6) % 7; // Shift so Monday = 0
  for (let i = 0; i < offset; i++) {
    grid.appendChild(document.createElement('div'));
  }

  for (let day = 1; day <= lastDay.getDate(); day++) {
    const date = new Date(2025, month, day);
    const formatted = `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')}`;

    const div = document.createElement('div');
    div.className = 'day';

    const isSunday = date.getDay() === 0;
    if (holidays[formatted]) {
      div.classList.add('holiday');
      div.innerHTML = `<strong>${day}</strong><br>${holidays[formatted]}`;
    } else if (isSunday) {
      div.classList.add('sunday');
      div.innerHTML = `<strong>${day}</strong><br>Sunday`;
    } else {
      div.innerHTML = `<strong>${day}</strong>`;
    }

    grid.appendChild(div);
  }

  section.appendChild(grid);
  calendarContainer.appendChild(section);
}