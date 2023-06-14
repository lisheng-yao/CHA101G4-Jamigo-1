function() {
    document.addEventListener('DOMContentLoaded', function () {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
            initialView: 'dayGridMonth',
            locale: 'zh-tw',
            navLinks: true,
            headerToolbar: {
                left: 'prev, next today',
                center: 'title',
                right: 'dayGridMonth, timeGridWeek, timeGridDay'
            }
        });
        calendar.render();
    });
}


