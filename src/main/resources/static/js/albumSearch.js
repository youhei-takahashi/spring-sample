document.addEventListener('DOMContentLoaded', () => {
    // フォーム要素とテーブルの tbody 要素を取得
    const titleInput = document.getElementById('title');
    const albumTableBody = document.querySelector('.table tbody');

    // 検索処理を実行中のフラグ（多重実行防止用）
    let isSearching = false;

    // フォームと入力フィールドが存在する場合のみ処理を実行
    if (titleInput && albumTableBody) {

        // input イベントは、ユーザーがテキストフィールドの値を変更するたびに発生します。
        titleInput.addEventListener('input', () => {
            // 遅延処理 (Debounce) のためのタイマーをクリア
            if (window.searchTimer) {
                clearTimeout(window.searchTimer);
            }

            // ユーザーが入力操作を終えてから300ms後に検索を実行
            window.searchTimer = setTimeout(performSearch, 300);
        });

        // 初回ロード時にも検索を実行する場合
        // performSearch();
    } else {
        console.warn('検索に必要なDOM要素が見つかりませんでした。');
    }

    /**
     * 非同期でアルバム検索を実行する関数
     */
    function performSearch() {
        if (isSearching) {
            return; // 既に検索中の場合はスキップ
        }

        // 入力された検索キーワードを取得
        const titleKeyword = titleInput.value;

        // API エンドポイントの URL を構築
        const url = `/api/albums?title=${encodeURIComponent(titleKeyword)}`;

        isSearching = true; // 検索開始

        // 1. fetch API を使用して非同期で GET リクエストを送信
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then(albums => {
                // 2. 既存のテーブルの中身をクリア
                albumTableBody.innerHTML = '';

                // 3. 取得したアルバムデータに基づいて新しい行を生成し、テーブルに追加
                if (albums && albums.length > 0) {
                    albums.forEach(album => {
                        const newRow = createAlbumTableRow(album);
                        albumTableBody.appendChild(newRow);
                    });
                } else {
                    // 検索結果がない場合のメッセージ
                    const noResultRow = document.createElement('tr');
                    noResultRow.innerHTML = `<td colspan="6" class="text-center">該当するアルバムは見つかりませんでした。</td>`;
                    albumTableBody.appendChild(noResultRow);
                }
            })
            .catch(error => {
                console.error('アルバム検索中にエラーが発生しました:', error);
                alert('アルバム検索中にエラーが発生しました。詳細はコンソールを確認してください。');
            })
            .finally(() => {
                isSearching = false; // 検索終了
            });
    }

    /**
     * アルバムオブジェクトからテーブル行 (<tr>) 要素を作成するヘルパー関数
     * @param {object} album - AlbumViewModel に対応するオブジェクト
     * @returns {HTMLTableRowElement} 作成された <tr> 要素
     */
    function createAlbumTableRow(album) {
        const row = document.createElement('tr');
        const detailUrl = `/albums/${album.albumId}`;
        const editUrl = `/albums/${album.albumId}/edit`;
        const deleteUrl = `/albums/${album.albumId}/delete`;

        row.innerHTML = `
            <td>${album.albumId}</td>
            <td><a href="${detailUrl}">${album.title}</a></td>
            <td>${album.artist}</td>
            <td>${album.releaseDate}</td>
            <td>${album.musicCount}</td>
            <td>
                <div class="d-flex">
                    <form action="${editUrl}" method="get">
                        <button type="submit" class="btn btn-warning btn-sm mx-2">編集</button>
                    </form>
                    <form action="${deleteUrl}" method="post">
                        <button type="submit" class="btn btn-danger btn-sm mx-2">削除</button>
                    </form>
                </div>
            </td>
        `;
        return row;
    }
});