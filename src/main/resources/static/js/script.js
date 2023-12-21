function getVacancyIdFromUrl() {
    const path = window.location.pathname;
    const parts = path.split('/');
    // URL має формат /analyse/{vacancyId}
    return parts[parts.indexOf('analyse') + 1];
}
function updateAnalysisResults() {
    var vacancyId = getVacancyIdFromUrl();
    var selectedMethod = document.getElementById('methodSelection').value;

    fetch(`/analyse/${vacancyId}/json?method=${selectedMethod}`)
        .then(response => response.json())
        .then(data => {
            // Оновлення DOM відповідно до отриманих даних
            updatePageContent(data, selectedMethod);

        })
        .catch(error => console.error('Error:', error));
}

function updatePageContent(data, selectedMethod) {
    // Оновлення вагових коєфіцієнтів
    var weightsInfo = document.getElementById('weightsInfo');
    if (selectedMethod === 'weighted' && data.weightsForWeighted) {
        weightsInfo.textContent = 'Вагові коєфіцієнти (Weighted): ' + data.weightsForWeighted.join(', ');
    } else {
        weightsInfo.textContent = 'Вагові коєфіцієнти: Недоступні';
    }

    // Оновлення списку топ-3 кандидатів
    var topCandidatesContainer = document.getElementById('topCandidatesContainer');
    topCandidatesContainer.innerHTML = ''; // Очищення поточного списку
    if (data.topCandidates) {
        data.topCandidates.forEach(candidate => {
            var candidateInfo = document.createElement('p');
            candidateInfo.textContent = `${candidate.id}: ${candidate.name}, Зарплата: ${candidate.desired_salary}`;
            topCandidatesContainer.appendChild(candidateInfo);
        });
    }

    // Оновлення стовпця "Відстань/Відхилення"
    data.normalizedCandidates.forEach(candidate => {
        var distanceOrDeviationCell = document.getElementById('distance-' + candidate.id);
        if (distanceOrDeviationCell) {
            var distanceOrDeviation = selectedMethod === 'weighted' && data.distances
                ? data.distances[candidate.id]
                : selectedMethod === 'preemptive' && data.deviations
                    ? data.deviations[candidate.id]
                    : 'Н/Д';
            distanceOrDeviationCell.textContent = distanceOrDeviation;
        }
    });
}