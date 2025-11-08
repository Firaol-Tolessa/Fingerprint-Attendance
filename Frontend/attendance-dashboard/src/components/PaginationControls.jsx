import React from 'react';

function PaginationControls({ pageData, onNext, onPrev }) {
  if (!pageData) return null;

  const { number, totalPages, totalElements, first, last } = pageData;

  return (
    <div className="pagination">
      <button onClick={onPrev} disabled={first}>
        &larr; Previous
      </button>
      <span>
        Page <strong>{number + 1}</strong> of <strong>{totalPages}</strong>
        (Total: {totalElements} records)
      </span>
      <button onClick={onNext} disabled={last}>
        Next &rarr;
      </button>
    </div>
  );
}

export default PaginationControls;