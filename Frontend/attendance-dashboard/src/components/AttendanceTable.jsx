import React from 'react';

function AttendanceTable({ events }) {
  if (!events || events.length === 0) {
    return <p>No attendance data found.</p>;
  }

  return (
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Employee ID</th>
          <th>Timestamp</th>
          <th>Device ID</th>
          <th>Event Hash</th>
        </tr>
      </thead>
      <tbody>
        {events.map(event => (
          <tr key={event.id}>
            <td>{event.id}</td>
            <td>{event.employeeId}</td>
            {/* Format timestamp for readability */}
            <td>{new Date(event.eventTimestamp).toLocaleString()}</td>
            <td>{event.deviceId}</td>
            <td>{event.eventHash}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default AttendanceTable;